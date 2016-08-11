# spring-integration-sftp
Spring Integration SFTP Example

### Some SFTP Notes

* You should always keep StrictHostKeyChecking as 'yes'. Putting to 'no' will make you susceptible to the 'man in the middle' attack (not recommended).

* Option 1: Use a private and public key combination (See my Evernote note on how to generate this combination).
  * Username is required (user).
  * Password is not required (password).
  * The private key is required and can be referenced as a file on the classpath (privateKey).
  * The private key passphrase is not required but highly recommended (privateKeyPassphrase). The passphrase is an option when generating the private and public key combination.
  * If you set the allow unknown keys to false (allowUnknownKeys), then you need to supply a known host file so that you can verify the servers identitfy (recommended). If you set this to true, then it will automatically accept the RSA footprint of the server (not recommended).

* Option 2: Use a username and password approach. 
  * Username is required.
