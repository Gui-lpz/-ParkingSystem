package model.entities;

/**
 *
 * @author Guiselle López
 */
public abstract class User {
    
    private String id;
    private String name;
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    

    public User(String id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }
    
  public abstract boolean verifyUserLogin(String[] loginDetails); //firma de método 
    
  // método intencionalmente polimórfico, pero que vamos a usar
  // si los usuarios se autentican de forma diferente
  public boolean verifyUserLogin(String[] loginDetails, int id){  //método polimorfico, la comparacion de parametros debe ser diferente
             return username.equals(loginDetails[0]) &&
               password.equals(loginDetails[1]);
              
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
