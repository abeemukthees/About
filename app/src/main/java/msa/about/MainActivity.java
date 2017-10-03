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

package msa.about;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import msa.aboutscreen.AboutUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAboutActivity();
            }
        });
    }

    private void openAboutActivity() {
        startActivity(AboutUI.getAboutUI(AboutUI.newBuilder()
                .context(this)
                .aboutText(getString(R.string.dummy_about_text))
                .companyLogo(R.drawable.logo_about)
                .facebookId("Your Facebook Id")
                .facebookUserName("Your Facebook userName")
                .twitterId("Your Twitter Id")
                .googlePlusId("Your Google Plus Id")
                .termsAndConditionsUrl("https://github.com/abeemukthees")
                .appVersion("1.0.0")
                .footerText("Made with love in Bengaluru")));
    }
}
