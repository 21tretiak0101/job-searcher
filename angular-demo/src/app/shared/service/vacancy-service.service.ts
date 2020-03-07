import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

export interface Vacancy {
  title: string;
  salary: string;
  city: string;
  companyName: string;
  vacancyURL: string;
  date: string;
}

@Injectable({
  providedIn: 'root'
})
export class VacancyService{

  constructor(private http: HttpClient) { }

  URL: string = 'http://localhost:8080/api/vacancies';

  getVacancies({city, technology, website}): Observable<Vacancy[]>{

    return this.http.get<Vacancy[]>(
      `${this.URL}/${city}/${technology}/${website}`);
  }
}
