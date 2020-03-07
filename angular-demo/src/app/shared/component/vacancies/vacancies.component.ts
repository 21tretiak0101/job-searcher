import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Vacancy, VacancyService} from "../../service/vacancy-service.service";
import {NgAnimateScrollService} from "ng-animate-scroll";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-vacancies',
  templateUrl: './vacancies.component.html',
  styleUrls: ['./vacancies.component.scss']
})
export class VacanciesComponent implements OnInit {

  constructor(private service: VacancyService, private animate: NgAnimateScrollService) { }

  vacancies: Vacancy[];
  page:number = 1;
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

  websites = {
    tb: 'Tut.by',
    bc: 'Belmeta.com'
  };

  technologies = {
    ja: 'Java',
    js: 'JavaScript',
    go: 'Go',
    ru: 'Ruby',
    ph: 'PHP'
  };

  form: FormGroup;

  ngOnInit(): void {
    this.form = new FormGroup({
      city: new FormControl('mi'),
      technology: new FormControl('ja'),
      website: new FormControl('tb')
    });
  }

  getVacancies(vacancy) : void{
    this.service.getVacancies(vacancy)
      .subscribe(response => {
        this.vacancies = response;
        this.collectionSize = this.vacancies.length;
        console.log(response);
      })
  }

  getFocus() {
    this.animate.scrollToElement('result', 650);
  }

  postForm() {
    const city = this.form.get('city').value;
    const website = this.form.get('website').value;
    const technology = this.form.get('technology').value;
    console.log(this.cities[city], this.websites[website], this.technologies[technology]);

    const vacancy = {
      city: this.cities[city],
      technology: this.technologies[technology],
      website: this.websites[website]
    };

    this.getVacancies(vacancy);
  }
}
