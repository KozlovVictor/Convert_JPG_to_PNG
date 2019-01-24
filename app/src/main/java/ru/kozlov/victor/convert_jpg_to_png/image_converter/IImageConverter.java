package ru.kozlov.victor.convert_jpg_to_png.image_converter;

import ru.kozlov.victor.convert_jpg_to_png.image_path.IImagePath;

public interface IImageConverter {

    void convertImage(IImagePath imagePath);
}
