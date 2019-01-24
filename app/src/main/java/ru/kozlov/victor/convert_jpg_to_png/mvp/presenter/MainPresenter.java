package ru.kozlov.victor.convert_jpg_to_png.mvp.presenter;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import ru.kozlov.victor.convert_jpg_to_png.App;
import ru.kozlov.victor.convert_jpg_to_png.image_converter.JpgToPngImageConverter;
import ru.kozlov.victor.convert_jpg_to_png.image_path.JpgImagePath;
import ru.kozlov.victor.convert_jpg_to_png.mvp.view.MainView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private JpgToPngImageConverter converter;
    private JpgImagePath jpgImagePath;

    public MainPresenter() {
        converter = new JpgToPngImageConverter();
    }

    public void selectImageButtonClick() {
        getViewState().selectImage();
    }

    public void convertButtonClick() {
        Completable completable = Completable.fromAction(new Action() {
            @Override
            public void run() {
                converter.convertImage(jpgImagePath);
            }
        });
        completable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

//        if (converter.convertImage(jpgImagePath))
//            Toast.makeText(App.getInstance().getApplicationContext(), "File converted successfully", Toast.LENGTH_LONG);
//        else
//            Toast.makeText(App.getInstance().getApplicationContext(), "Fail file convert", Toast.LENGTH_LONG);
    }

    public void setSelectedImage(Uri fullImageUri) {
        if (fullImageUri != null) {
            jpgImagePath = new JpgImagePath();
            jpgImagePath.setImagePath(fullImageUri);
            getViewState().setImageToConvert(BitmapFactory.decodeFile(fullImageUri.getPath()));
        } else
            Toast.makeText(App.getInstance().getApplicationContext(), "ImageURi = null", Toast.LENGTH_LONG);
    }
}
