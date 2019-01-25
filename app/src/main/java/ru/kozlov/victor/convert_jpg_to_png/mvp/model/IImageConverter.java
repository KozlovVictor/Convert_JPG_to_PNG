package ru.kozlov.victor.convert_jpg_to_png.mvp.model;

import io.reactivex.Completable;
import ru.kozlov.victor.convert_jpg_to_png.image_path.IImagePaths;

public interface IImageConverter {

    Completable convertImage(IImagePaths targetImage);
}
