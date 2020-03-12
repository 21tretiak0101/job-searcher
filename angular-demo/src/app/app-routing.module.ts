import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {VacanciesComponent} from "./shared/component/vacancies/vacancies.component";


const routes: Routes = [{
  path: 'vac', component: VacanciesComponent,
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
