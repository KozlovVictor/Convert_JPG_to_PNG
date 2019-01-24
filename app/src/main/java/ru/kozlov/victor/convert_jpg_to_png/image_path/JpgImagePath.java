package ru.kozlov.victor.convert_jpg_to_png.image_path;

import android.net.Uri;

public class JpgImagePath implements IImagePath {
    Uri fullImageUri;

    @Override
    public void setImagePath(Uri fullImageUri) {
        this.fullImageUri = fullImageUri;
    }
}
