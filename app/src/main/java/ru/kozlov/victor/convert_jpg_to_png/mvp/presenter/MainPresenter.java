package ru.kozlov.victor.convert_jpg_to_png.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.File;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.kozlov.victor.convert_jpg_to_png.image_converter.JpgToPngImageConverter;
import ru.kozlov.victor.convert_jpg_to_png.image_path.ConvertImagePaths;
import ru.kozlov.victor.convert_jpg_to_png.mvp.view.MainView;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private final String CONVERT_SUCCESS = "File converted successfully";
    private final String CONVERT_FAIL = "Fail file convert";
    private final String CONVERT_CANCEL = "Cancel file convert";

    private JpgToPngImageConverter converter;
    private ConvertImagePaths convertImagePaths;
    private Disposable convertationSubscription;

    public MainPresenter() {
        converter = new JpgToPngImageConverter();
    }

    public void imageClick() {
        getViewState().pickImage();
    }

    public void convertButtonClick() {
        converter.convertImage(convertImagePaths)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        convertationSubscription = d;
                    }

                    @Override
                    public void onComplete() {
                        getViewState().showConversionResultMessage(CONVERT_SUCCESS);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showConversionResultMessage(CONVERT_FAIL);
                        e.printStackTrace();
                    }
                });
    }

    public void setSelectedImage(String imagePath) {
        if (imagePath != null) {
            convertImagePaths = new ConvertImagePaths();
            convertImagePaths.setSourceImagePath(imagePath);
            showImageOnView(imagePath);
//            chooseDestinationPath();
            String destPath = imagePath + File.separator + "result.png";
            setDestinationPath(destPath);
        } else
            Timber.d("Full Image Uri = null");
    }

    private void chooseDestinationPath() {
        getViewState().chooseDestinationPath();
    }

    public void setDestinationPath(String imagePath) {
        convertImagePaths.setDestinationImagePath(imagePath);
    }

    private void showImageOnView(String imagePath) {
        getViewState().showImage(imagePath);
    }
}
