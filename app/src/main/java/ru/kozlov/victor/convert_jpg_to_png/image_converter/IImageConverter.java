package ru.kozlov.victor.convert_jpg_to_png.image_converter;

import io.reactivex.CompletableObserver;
import ru.kozlov.victor.convert_jpg_to_png.image_path.IImagePath;

public interface IImageConverter {

    CompletableObserver convertImage(IImagePath targetImage);
}
