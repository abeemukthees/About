/*
 * Copyright 2017, Abhi Muktheeswarar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package msa.aboutscreen;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.DrawableRes;

/**
 * Created by Abhimuktheeswarar on 03-10-2017.
 */

public class AboutUI {

    static final String COMPANY_LOGO = "COMPANY_LOGO";
    static final String ABOUT_TEXT = "ABOUT_TEXT";
    static final String FACEBOOK_ID = "FACEBOOK_ID";
    static final String FACEBOOK_USERNAME = "FACEBOOK_USERNAME";
    static final String TWITTER_ID = "TWITTER_ID";
    static final String GOOGLE_PLUS_ID = "GOOGLE_PLUS_ID";
    static final String TERMS_AND_CONDITIONS_URL = "TERMS_AND_CONDITIONS_URL";
    static final String APP_VERSION = "APP_VERSION";
    static final String FOOTER_TEXT = "FOOTER_TEXT";
    static final String URL = "URL";

    @DrawableRes
    private int companyLogo;

    private String aboutText, facebookId, facebookUserName, twitterId, googlePlusId, termsAndConditionsUrl, appVersion, footerText, url;

    private Context context;

    private AboutUI(Builder builder) {
        companyLogo = builder.companyLogo;
        aboutText = builder.aboutText;
        facebookId = builder.facebookId;
        facebookUserName = builder.facebookUserName;
        twitterId = builder.twitterId;
        googlePlusId = builder.googlePlusId;
        termsAndConditionsUrl = builder.termsAndConditionsUrl;
        appVersion = builder.appVersion;
        footerText = builder.footerText;
        url = builder.url;
        context = builder.context;
    }


    private AboutUI() {
    }

    public static Intent getAboutUI(Builder authUiBuilder) {
        Intent intent = new Intent(authUiBuilder.context, AboutActivity.class);
        intent.putExtra(COMPANY_LOGO, authUiBuilder.companyLogo);
        intent.putExtra(ABOUT_TEXT, authUiBuilder.aboutText);
        intent.putExtra(FACEBOOK_ID, authUiBuilder.facebookId);
        intent.putExtra(FACEBOOK_USERNAME, authUiBuilder.facebookUserName);
        intent.putExtra(TWITTER_ID, authUiBuilder.twitterId);
        intent.putExtra(GOOGLE_PLUS_ID, authUiBuilder.googlePlusId);
        intent.putExtra(TERMS_AND_CONDITIONS_URL, authUiBuilder.termsAndConditionsUrl);
        intent.putExtra(APP_VERSION, authUiBuilder.appVersion);
        intent.putExtra(FOOTER_TEXT, authUiBuilder.footerText);
        intent.putExtra(URL, authUiBuilder.url);
        return intent;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private int companyLogo;
        private String aboutText;
        private String facebookId;
        private String facebookUserName;
        private String twitterId;
        private String googlePlusId;
        private String termsAndConditionsUrl;
        private String appVersion;
        private String footerText;
        private String url;
        private Context context;

        private Builder() {
        }

        public Builder companyLogo(int companyLogo) {
            this.companyLogo = companyLogo;
            return this;
        }

        public Builder aboutText(String aboutText) {
            this.aboutText = aboutText;
            return this;
        }

        public Builder facebookId(String facebookId) {
            this.facebookId = facebookId;
            return this;
        }

        public Builder facebookUserName(String facebookUserName) {
            this.facebookUserName = facebookUserName;
            return this;
        }

        public Builder twitterId(String twitterId) {
            this.twitterId = twitterId;
            return this;
        }

        public Builder googlePlusId(String googlePlusId) {
            this.googlePlusId = googlePlusId;
            return this;
        }

        public Builder termsAndConditionsUrl(String termsAndConditionsUrl) {
            this.termsAndConditionsUrl = termsAndConditionsUrl;
            return this;
        }

        public Builder appVersion(String appVersion) {
            this.appVersion = appVersion;
            return this;
        }

        public Builder footerText(String footerText) {
            this.footerText = footerText;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public AboutUI build() {
            return new AboutUI(this);
        }
    }
}
