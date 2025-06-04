package ca.robertgleason.ecommerce.payload;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class APIResponse {
    public String message;
    private Boolean status;
}
