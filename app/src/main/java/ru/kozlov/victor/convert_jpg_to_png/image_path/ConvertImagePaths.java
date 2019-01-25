package ru.kozlov.victor.convert_jpg_to_png.image_path;

public class ConvertImagePaths implements IImagePaths {

    private String sourceImagePath;
    private String destinationImagePath;

    @Override
    public String getSourceImagePath() {
        return sourceImagePath;
    }

    @Override
    public void setSourceImagePath(String sourceImagePath) {
        this.sourceImagePath = sourceImagePath;
    }

    @Override
    public String getDestinationImagePath() {
        return destinationImagePath;
    }

    @Override
    public void setDestinationImagePath(String destinationImagePath) {
        this.destinationImagePath = destinationImagePath;
    }
}
