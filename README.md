# FerBot


## Development

### Facebook Page
Create new Facebook Page. Messages sent to this page will be forwarded to this application.

### Facebook App
* Go to [Facebook Developers](https://developers.facebook.com/) and create new app.
* SetUp Messenger on your new app
    * Token Generation -> choose your newly created page
    * Webhooks
        * CallBack URL -> url to webhook running on your machine (ngrok)
        * Verify Token -> Same token that you set in your properties
        * Subscription Fields -> messages, messaging_postbacks, messaging_referrals
