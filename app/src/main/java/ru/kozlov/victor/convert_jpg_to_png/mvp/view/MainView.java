package ru.kozlov.victor.convert_jpg_to_png.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndStrategy.class)
public interface MainView extends MvpView {

    void pickImage();

    void showImage(String imagePath);

    void showConversionResultMessage(String message);

    void chooseDestinationPath();
}
