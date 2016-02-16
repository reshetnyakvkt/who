package questionnaires.extras;
import questionnaires.domain.FormValue;
import java.util.List;

/**
 * Created by D2R2 on 28.10.2015.
 */
public class FormExporter {
    private final String C_DEL = ";";

    public final String getCSV(List<FormValue> formValueList){
        return getCSV(formValueList, C_DEL);
    }

    public final String getCSV(List<FormValue> formValueList, String del) {
        String res="";
        for (int i=0; i < formValueList.size(); i++){
            if (i > 0) {
                res += "\r\n";
            }
            res += formValueList.get(i).getFormId() + del;
            res += formValueList.get(i).getId() + del;
            res += formValueList.get(i).getValStr();
        }
        return res;
    }

}
