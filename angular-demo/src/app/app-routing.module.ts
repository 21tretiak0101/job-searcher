import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {AboutPageComponent} from './about-page/about-page.component';
import {ContactsPageComponent} from './contacts-page/contacts-page.component';
import {MainPageComponent} from './main-page/main-page.component';


const routes: Routes = [
  {path: 'main', component: MainPageComponent},
  {path: 'about', component: AboutPageComponent},
  {path: 'contacts', component: ContactsPageComponent},
  { path: '', redirectTo: '/main', pathMatch: 'full'},
  { path: '**', redirectTo: '/main'}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
