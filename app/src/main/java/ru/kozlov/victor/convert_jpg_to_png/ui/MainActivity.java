package ru.kozlov.victor.convert_jpg_to_png.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.io.IOException;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.kozlov.victor.convert_jpg_to_png.App;
import ru.kozlov.victor.convert_jpg_to_png.R;
import ru.kozlov.victor.convert_jpg_to_png.mvp.presenter.MainPresenter;
import ru.kozlov.victor.convert_jpg_to_png.mvp.view.MainView;
import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    public static final int PERMISSION_REQUEST_CODE = 0;
    private static final String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static final String SELECT_PICTURE = "Select picture";
    private static final int PICK_IMAGE_REQUEST_CODE = 1;

    @BindView(R.id.iv_image) ImageView iv_imageToConvert;
    @BindView(R.id.btn_convert) Button btn_convert;
    @BindString(R.string.permission_required) String permissionRequired;
    @BindString(R.string.request_permisson_message) String requestPermissionMessage;
    @BindString(R.string.positive_button) String positiveButton;

    @InjectPresenter
    MainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        iv_imageToConvert.setOnClickListener(view -> presenter.imageClick());
        btn_convert.setOnClickListener(view -> presenter.convertButtonClick());

    }

    @Override
    public void showImage(String imagePath) {
        Bitmap bitmap;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(imagePath));
            iv_imageToConvert.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showConversionResultMessage(String message) {
        Toast.makeText(App.getInstance().getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void chooseDestinationPath() {
        //TODO fill this method
    }

    @ProvidePresenter
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Override
    public void pickImage() {
        if (!checkPermissions()) {
            requestPermissions();
            return;
        }
        onPermissionGranted();
    }

    private boolean checkPermissions() {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onPermissionGranted();
                } else {
                    new AlertDialog.Builder(this)
                            .setTitle(permissionRequired)
                            .setMessage(requestPermissionMessage)
                            .setPositiveButton(positiveButton, (dialogInterface, i) -> requestPermissions())
                            .setOnCancelListener(dialogInterface -> requestPermissions())
                            .create()
                            .show();
                }
        }
    }

    private void onPermissionGranted() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, SELECT_PICTURE), PICK_IMAGE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Timber.d("PICK_IMAGE_REQUEST_CODE = " + requestCode + " RESULT_CODE = " + requestCode);
        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri imageUri = null;
            if (data != null) imageUri = data.getData();
            presenter.setSelectedImage(imageUri != null ? imageUri.toString() : null);
        }
    }
}
