package ru.kozlov.victor.convert_jpg_to_png.UI;

import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import ru.kozlov.victor.convert_jpg_to_png.R;
import ru.kozlov.victor.convert_jpg_to_png.mvp.presenter.MainPresenter;

public class MainActivity extends MvpAppCompatActivity {

    @InjectPresenter
    MainPresenter mainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @ProvidePresenter
    public MainPresenter providePresenter() {
        return new MainPresenter();
    }
}
