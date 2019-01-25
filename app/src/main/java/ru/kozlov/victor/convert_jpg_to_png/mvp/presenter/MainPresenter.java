package ru.kozlov.victor.convert_jpg_to_png.mvp.presenter;

import android.graphics.BitmapFactory;
import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.kozlov.victor.convert_jpg_to_png.image_converter.JpgToPngImageConverter;
import ru.kozlov.victor.convert_jpg_to_png.image_path.JpgImagePath;
import ru.kozlov.victor.convert_jpg_to_png.mvp.view.MainView;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private final String CONVERT_SUCCESS = "File converted successfully";
    private final String CONVERT_FAIL = "Fail file convert";

    private JpgToPngImageConverter converter;
    private JpgImagePath jpgImagePath;
    private Disposable convertationSubscription;

    public MainPresenter() {
        converter = new JpgToPngImageConverter();
    }

    public void selectImageButtonClick() {
        getViewState().selectImage();
    }

    public void convertButtonClick() {
        converter.convertImage(jpgImagePath)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        convertationSubscription = d;
                    }

                    @Override
                    public void onComplete() {
                        getViewState().showResultConvertMessage(CONVERT_SUCCESS);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showResultConvertMessage(CONVERT_FAIL);
                    }
                });
    }

    public void setSelectedImage(Uri fullImageUri) {
        if (fullImageUri != null) {
            jpgImagePath = new JpgImagePath();
            jpgImagePath.setImagePath(fullImageUri);
            getViewState().setImageToConvert(BitmapFactory.decodeFile(fullImageUri.getPath()));
        } else
            Timber.d("Full Image Uri = null");
    }
}
