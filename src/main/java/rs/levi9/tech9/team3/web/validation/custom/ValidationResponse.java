package rs.levi9.tech9.team3.web.validation.custom;

import java.util.ArrayList;
import java.util.List;

public class ValidationResponse {

    List<ValidationResponseItem> exceptions;

    public ValidationResponse() {
        this.exceptions = new ArrayList<>();
    }

    public ValidationResponse(List<ValidationResponseItem> exceptions) {
        this.exceptions = exceptions;
    }

    public List<ValidationResponseItem> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<ValidationResponseItem> exceptions) {
        this.exceptions = exceptions;
    }
    
    public ValidationResponse addItem(String field, String description) {
        this.exceptions.add(new ValidationResponseItem(field, description));
        return this;
    }
    
    public class ValidationResponseItem {
        
        String field;
        String message;
        
        public ValidationResponseItem() {
            
        }
        
        public ValidationResponseItem(String field, String description) {
            this.field = field;
            this.message = description;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

}
