# FerBot


## Development
* `cp src/main/resources/application-sample.yaml src/main/resources/application-dev.yaml`
* adjust your machine specific settings
* run `./gradlew bootRun`

### Facebook Page
Create new Facebook Page. Messages sent to this page will be forwarded to this application.

### Facebook App
* Go to [Facebook Developers](https://developers.facebook.com/) and create new app.
* SetUp Messenger on your new app
    * Token Generation -> choose your newly created page -> save in app properties
    * Webhooks
        * CallBack URL -> url to webhook running on your machine (ngrok)
        * Verify Token -> Same token that you set in your properties
        * Subscription Fields -> messages, messaging_postbacks, messaging_referrals
    * Select your Facebook page and connect(Subscribe)
