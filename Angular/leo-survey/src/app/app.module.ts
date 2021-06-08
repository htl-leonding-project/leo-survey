import { MatTableModule } from '@angular/material/table';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { CommonModule } from "@angular/common";


import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FillOutSurveyComponent } from './fill-out-survey/fill-out-survey.component';
import { AppRoutingModule } from './app-routing.module';
import { GetResultsComponent } from './get-results/get-results.component';

@NgModule({
  declarations: [
    AppComponent,
    FillOutSurveyComponent,
    GetResultsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    MatTableModule,
    MatCheckboxModule,
    BrowserAnimationsModule,
    CommonModule,
    FormsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
