package hanalyst.application.hanalystclub.Util;

import android.content.Context;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import hanalyst.application.hanalystclub.R;

public class FieldValidation {
    private Context context;

    public FieldValidation(Context context) {
        this.context = context;
    }

    public Boolean validateNotBlank(EditText textField) {
        if (textField.getText().toString().length() == 0) {
            textField.setError(context.getString(R.string.field_should_not_be_empty));
            return false;
        }
        if (textField.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD &&
                textField.getText().toString().length() > 6) {
            textField.setError(context.getString(R.string.password_length_should_be_greater_than_6));
            return false;
        }
        return true;
    }

    public Boolean isEmpty(EditText textField) {
        return textField.getText().toString().length() == 0;
    }

    public Boolean valueMatches(EditText textField, EditText textField1) {
        return textField.getText().toString().equals(textField1.getText().toString());
    }

    public Boolean equalsWith(EditText text, String target) {
        return text.getText().toString().matches(target);
    }

    public Boolean validateWithCustomMessage(EditText textField, String customMessage) {
        textField.setError(customMessage);
        return false;
    }

    public Boolean selectedItemPosition(Spinner spinner) {
        if (spinner.getSelectedItemPosition() > 0) return true;
        ((TextView) spinner.getSelectedView()).setError(context.getString(R.string.field_should_be_selected));
        return false;
    }
}
