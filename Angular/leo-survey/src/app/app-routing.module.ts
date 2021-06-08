import { FillOutSurveyComponent } from './fill-out-survey/fill-out-survey.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [
  { path: 'fillout', component: FillOutSurveyComponent }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
