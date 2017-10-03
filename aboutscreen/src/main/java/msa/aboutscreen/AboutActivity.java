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

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = AboutActivity.class.getSimpleName();

    private Toolbar toolbar;

    private TextView textView_aboutText, textView_version, textView_footer;

    private ImageView imageView_logo;

    private RelativeLayout relativeLayout_facebook, relativeLayout_twitter, relativeLayout_googlePlus, relativeLayout_termsAndConditions, relativeLayout_version;

    private View divider0, divider1, divider2, divider3;

    @DrawableRes
    private int companyLogo;

    private String aboutText, facebookId, facebookUserName, twitterId, googlePlusId, termsAndConditionsUrl, appVersion, footerText, url;
    private int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView_logo = (ImageView) findViewById(R.id.image_logo);

        textView_aboutText = (TextView) findViewById(R.id.text_about);
        textView_version = (TextView) findViewById(R.id.text_version);
        textView_footer = (TextView) findViewById(R.id.text_footer);

        relativeLayout_facebook = (RelativeLayout) findViewById(R.id.relative_facebook);
        relativeLayout_twitter = (RelativeLayout) findViewById(R.id.relative_twitter);
        relativeLayout_googlePlus = (RelativeLayout) findViewById(R.id.relative_googlePlus);
        relativeLayout_termsAndConditions = (RelativeLayout) findViewById(R.id.relative_terms);
        relativeLayout_version = (RelativeLayout) findViewById(R.id.relative_version);

        divider0 = findViewById(R.id.divider_0);
        divider1 = findViewById(R.id.divider_1);
        divider2 = findViewById(R.id.divider_2);
        divider3 = findViewById(R.id.divider_3);

        if (getIntent() != null) setupViews(getIntent());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.relative_facebook) {
            openFacebook();
        } else if (id == R.id.relative_twitter) {
            openTwitter();
        } else if (id == R.id.relative_googlePlus) {
            openGooglePlus();
        } else if (id == R.id.relative_terms) {
            openTermsAndConditions();
        } else if (id == R.id.text_footer) {
            openDev();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        relativeLayout_facebook.setOnClickListener(this);
        relativeLayout_twitter.setOnClickListener(this);
        relativeLayout_googlePlus.setOnClickListener(this);
        relativeLayout_termsAndConditions.setOnClickListener(this);
        textView_footer.setOnClickListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        relativeLayout_facebook.setOnClickListener(null);
        relativeLayout_twitter.setOnClickListener(null);
        relativeLayout_googlePlus.setOnClickListener(null);
        relativeLayout_termsAndConditions.setOnClickListener(null);
        textView_footer.setOnClickListener(null);
    }

    private void setupViews(Intent intent) {

        companyLogo = intent.getIntExtra(AboutUI.COMPANY_LOGO, -1);
        aboutText = intent.getStringExtra(AboutUI.ABOUT_TEXT);
        facebookId = intent.getStringExtra(AboutUI.FACEBOOK_ID);
        facebookUserName = intent.getStringExtra(AboutUI.FACEBOOK_USERNAME);
        twitterId = intent.getStringExtra(AboutUI.TWITTER_ID);
        googlePlusId = intent.getStringExtra(AboutUI.GOOGLE_PLUS_ID);
        termsAndConditionsUrl = intent.getStringExtra(AboutUI.TERMS_AND_CONDITIONS_URL);
        appVersion = intent.getStringExtra(AboutUI.APP_VERSION);
        footerText = intent.getStringExtra(AboutUI.FOOTER_TEXT);
        url = intent.getStringExtra(AboutUI.URL);

        if (companyLogo != -1) imageView_logo.setImageResource(companyLogo);
        textView_aboutText.setText(aboutText);


        if (facebookId == null || facebookUserName == null) {
            relativeLayout_facebook.setVisibility(View.GONE);
            divider0.setVisibility(View.GONE);
        }

        if (twitterId == null) {
            relativeLayout_twitter.setVisibility(View.GONE);
            divider1.setVisibility(View.GONE);
        }

        if (googlePlusId == null) {
            relativeLayout_googlePlus.setVisibility(View.GONE);
            divider2.setVisibility(View.GONE);
        }

        if (termsAndConditionsUrl == null) {
            relativeLayout_termsAndConditions.setVisibility(View.GONE);
            divider3.setVisibility(View.GONE);
        }

        if (appVersion == null) {
            relativeLayout_version.setVisibility(View.GONE);
        } else textView_version.setText(appVersion);

        setupFooterText();
    }

    private void setupFooterText() {

        if (footerText != null && footerText.contains("love")) {
            Log.d(TAG, "contains love = " + footerText.indexOf("love") + " | " + footerText.indexOf("love") + 4);
            SpannableString ss = new SpannableString(footerText);
            Drawable d = ContextCompat.getDrawable(this, R.drawable.ic_hearts);
            d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            ImageSpan imageSpan = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
            ss.setSpan(imageSpan, footerText.indexOf("love"), footerText.indexOf("love") + 4, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            textView_footer.setText(ss);
        } else if (footerText != null) {
            textView_footer.setText(footerText);
        } else textView_footer.setVisibility(View.INVISIBLE);

    }

    private void openFacebook() {
        Intent facebook_intent;
        try {
            facebook_intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + facebookId));
            startActivity(facebook_intent);
        } catch (Exception e) {
            facebook_intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + facebookUserName));
            startActivity(facebook_intent);
        }
    }

    private void openTwitter() {
        Intent twitter_intent;
        try {
            twitter_intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter.com" + twitterId));
            twitter_intent.setType("application/twitter");
            startActivity(twitter_intent);
        } catch (Exception e) {
            twitter_intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + twitterId));
            startActivity(twitter_intent);
        }
    }

    private void openGooglePlus() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setClassName("com.google.android.apps.plus", "com.google.android.apps.plus.phone.UrlGatewayActivity");
            intent.putExtra("customAppUri", googlePlusId);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/" + googlePlusId + "/posts")));
        }
    }

    private void openTermsAndConditions() {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setShowTitle(true);
        builder.setToolbarColor(fetchPrimaryColor());
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(termsAndConditionsUrl));
    }

    private void openDev() {
        if (count > 6 && count < 10)
            Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
        if (count >= 10) {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.setShowTitle(true);
            builder.setToolbarColor(fetchPrimaryColor());
            CustomTabsIntent customTabsIntent = builder.build();
            if (url != null) {
                if (URLUtil.isValidUrl(url)) customTabsIntent.launchUrl(this, Uri.parse(url));
            } else {
                customTabsIntent.launchUrl(this, Uri.parse("https://about.me/abhimuktheeswarar"));
            }
        }
        count++;
    }

    private int fetchPrimaryColor() {
        TypedValue typedValue = new TypedValue();

        TypedArray a = obtainStyledAttributes(typedValue.data, new int[]{R.attr.colorPrimary});
        int color = a.getColor(0, 0);

        a.recycle();

        return color;
    }
}
