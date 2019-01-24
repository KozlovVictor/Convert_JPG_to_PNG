package ru.kozlov.victor.convert_jpg_to_png.mvp.presenter;

import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.kozlov.victor.convert_jpg_to_png.image_path.JpgImagePath;
import ru.kozlov.victor.convert_jpg_to_png.image_converter.JpgToPngImageConverter;
import ru.kozlov.victor.convert_jpg_to_png.image_path.PngImagePath;
import ru.kozlov.victor.convert_jpg_to_png.mvp.view.MainView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private JpgToPngImageConverter converter;
    private JpgImagePath jpgImagePath;
    private PngImagePath pngImagePath;

    public MainPresenter() {
        converter = new JpgToPngImageConverter();
    }

    public void selectImageButtonClick() {
        getViewState().selectImage();
    }

    public void convertButtonClick() {
        converter.convertImage(jpgImagePath);
    }

    public void setLoadedImage(Uri fullImageUri) {
        if (fullImageUri != null) {
            jpgImagePath = new JpgImagePath();
            jpgImagePath.setImagePath(fullImageUri);
        }
    }
}
