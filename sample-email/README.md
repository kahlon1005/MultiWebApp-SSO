##Readme
##=========

###standalone.xml
add below to the socket-binding-group

```
<outbound-socket-binding name="gmail-smtp">
   <remote-destination host="smtp.gmail.com" port="465"/>
</outbound-socket-binding>
```