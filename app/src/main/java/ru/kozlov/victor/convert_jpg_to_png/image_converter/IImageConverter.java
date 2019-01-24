package ru.kozlov.victor.convert_jpg_to_png.image_converter;

import io.reactivex.Completable;
import ru.kozlov.victor.convert_jpg_to_png.image_path.IImagePath;

public interface IImageConverter {

    Completable convertImage(IImagePath targetImage);
}
