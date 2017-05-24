package baltamon.mx.sendanemail;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by Baltazar Rodriguez on 22/05/2017.
 */

public class MainActivityViewHolder {
    private EditText editTextTo;
    private EditText editTextSubject;
    private EditText editTextBody;

    private ImageView imageView;

    private Button buttonLoadLocalImage;
    private Button buttonTakePhoto;
    private Button buttonSendEmail;

    public MainActivityViewHolder(View view){
        editTextTo = (EditText) view.findViewById(R.id.et_to_email);
        editTextSubject = (EditText) view.findViewById(R.id.et_subject_email);
        editTextBody = (EditText) view.findViewById(R.id.et_body_email);
        imageView = (ImageView) view.findViewById(R.id.iv_image);
        buttonLoadLocalImage = (Button) view.findViewById(R.id.btn_load_local_image);
        buttonTakePhoto = (Button) view.findViewById(R.id.btn_take_photo);
        buttonSendEmail = (Button) view.findViewById(R.id.btn_send_email);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Button getButtonLoadLocalImage() {
        return buttonLoadLocalImage;
    }

    public Button getButtonTakePhoto() {
        return buttonTakePhoto;
    }

    public EditText getEditTextTo() {
        return editTextTo;
    }

    public EditText getEditTextSubject() {
        return editTextSubject;
    }

    public EditText getEditTextBody() {
        return editTextBody;
    }

    public Button getButtonSendEmail() {
        return buttonSendEmail;
    }
}
