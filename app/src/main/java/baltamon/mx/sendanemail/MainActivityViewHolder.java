package baltamon.mx.sendanemail;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Baltazar Rodriguez on 22/05/2017.
 */

public class MainActivityViewHolder {
    private EditText editTextTo;
    private EditText editTextSubject;
    private EditText editTextBody;

    private Button buttonSendEmail;

    public MainActivityViewHolder(View view){
        editTextTo = (EditText) view.findViewById(R.id.et_to_email);
        editTextSubject = (EditText) view.findViewById(R.id.et_subject_email);
        editTextBody = (EditText) view.findViewById(R.id.et_body_email);
        buttonSendEmail = (Button) view.findViewById(R.id.btn_send_email);
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
