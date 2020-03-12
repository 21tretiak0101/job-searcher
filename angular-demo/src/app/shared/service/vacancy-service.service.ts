import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from '@angular/common/http';

import {URL} from '../../../environments/environment';
import {Vacancy, VacancyDTO} from '../interfaces';


@Injectable({
  providedIn: 'root'
})
export class VacancyService{

  constructor(private http: HttpClient) { }

  getVacancies(vacancy: VacancyDTO): Observable<Vacancy[]>{

    return this.http.get<Vacancy[]>(`${URL}/vacancies`, {
        params: {...vacancy}
      });
  }
}
