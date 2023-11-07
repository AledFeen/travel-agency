package bondarenko.travelagency.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangedList {
    List<ChangeDto> changeDtoList = new ArrayList<>();

    public void addChangeItem(ChangeDto item) {
        this.changeDtoList.add(item);
    }
}
