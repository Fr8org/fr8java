package co.fr8.data.interfaces.dto;

/**
 * TODO: Implement
 */
public class ActivityResponseDTO {

  private String type;
  private String body;

  public ActivityResponseDTO(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}
