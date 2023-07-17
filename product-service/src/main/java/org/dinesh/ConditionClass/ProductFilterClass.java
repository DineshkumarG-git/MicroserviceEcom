package org.dinesh.ConditionClass;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
/**
 * This class contains the filter that can be send by the server
 */
public class ProductFilterClass {

    private String partialName;
    private String ExactName;

    private String  Catergory ;

    private int limit ;

    private  int offset;

    @Override
    public int hashCode()
    {
          var hashcode  = partialName !=null ? partialName.hashCode() : 0;
          hashcode += (int) (hashcode  + (Catergory !=null ? Catergory.hashCode() : 0));
        hashcode += (int) (hashcode  + (ExactName !=null ? ExactName.hashCode() : 0));
        hashcode += (int) (hashcode  +  limit);
        hashcode+= (int) (hashcode + offset);
        return  hashcode ;
    }

    @Override
    public boolean equals(Object o)
    {
        var temp = (ProductFilterClass) o;
        return  temp.Catergory == this.Catergory && temp.ExactName == this.ExactName && temp.partialName == this.partialName && temp.limit == this.limit && temp.offset ==this.offset ;
    }



}
