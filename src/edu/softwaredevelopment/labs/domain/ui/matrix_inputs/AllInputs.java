package edu.softwaredevelopment.labs.domain.ui.matrix_inputs;

public class AllInputs {
    private FieldsInputs fields;
    private InitialInputs initials;

    public AllInputs() {
        this.initials = new InitialInputs();
    }

    public FieldsInputs getFields() {
        return fields;
    }

    public void setFields(FieldsInputs fields) {
        this.fields = fields;
    }

    public InitialInputs getInitials() {
        return initials;
    }

    public void setInitials(InitialInputs initials) {
        this.initials = initials;
    }
}
