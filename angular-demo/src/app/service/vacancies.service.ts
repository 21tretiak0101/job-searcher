import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from '@angular/common/http';

import {URL} from '../../environments/environment';
import {Vacancy, VacancyDTO} from '../interface/interfaces';


@Injectable({
  providedIn: 'root'
})
export class VacancyService{

  private GET_VACANCIES: string = `${URL}/vacancies`;

  constructor(private http: HttpClient) { }

  getVacancies(vacancy: VacancyDTO): Observable<Vacancy[]>{

    return this.http.get<Vacancy[]>(this.GET_VACANCIES, {
        params: {...vacancy}
      });
  }
}
