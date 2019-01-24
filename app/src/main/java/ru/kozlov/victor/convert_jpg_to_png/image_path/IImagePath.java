package ru.kozlov.victor.convert_jpg_to_png.image_path;

import android.net.Uri;

public interface IImagePath {

    void setImagePath(Uri fullImageUri);

    Uri getImagePath();
}
