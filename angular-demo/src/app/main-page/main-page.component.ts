import { Component, OnInit } from '@angular/core';
import {VacancyService} from '../service/vacancies.service';
import {NgAnimateScrollService} from 'ng-animate-scroll';
import {Vacancy, VacancyDTO} from '../interface/interfaces';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {

  vacancies: Vacancy[] = [];
  page: number = 1;
  pageSize: number = 7;
  collectionSize: number;

  cities = {
    gr: "Гродно",
    mi: 'Минск',
    br: 'Брест',
    go: 'Гомель',
    vi: 'Витебск',
    mo: 'Могилёв'
  };

  technologies = {
    ja: 'Java',
    js: 'JavaScript',
    go: 'Go',
    ru: 'Ruby',
    ph: 'PHP',
    ne: '.NET',
    py: 'Python',
    re: 'React',
    no: 'Node.js'
  };

  websites = {
    belmeta: 'Belmeta.com',
    tutby: 'Tut.by'
  };

  form: FormGroup;

  technologiesKeys = Object.keys(this.technologies);
  citiesKeys = Object.keys(this.cities);
  websitesKeys = Object.keys(this.websites);

  constructor(private service: VacancyService, private animate: NgAnimateScrollService) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      city: new FormControl('mi'),
      technology: new FormControl('ja'),
      website: new FormControl('belmeta')
    });
  }

  getVacancies(vacancy: VacancyDTO) : void {
    this.service.getVacancies(vacancy)
      .subscribe(response => {
        this.vacancies = response;
        this.collectionSize = this.vacancies.length;
        console.log(response);
      })
  }

  scroll(id: string) {
    this.animate.scrollToElement(id, 650);
  }

  submit() {
    const city = this.form.get('city').value;
    const website = this.form.get('website').value;
    const technology = this.form.get('technology').value;

    const vacancy: VacancyDTO = {
      city: this.cities[city],
      technology: this.technologies[technology],
      website
    };

    this.getVacancies(vacancy);
  }
}
