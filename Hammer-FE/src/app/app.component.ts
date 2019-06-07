import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { ApiService } from './services/api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Adress Manager';

  errorMessage: string;
  sizeUpdated: number;
  formUpdate: FormGroup;
  processing: boolean = false;

  stringDateModel: string = new Date().toString();


  constructor(private formBuilder: FormBuilder, protected apiService: ApiService) { }

  reset() {
    this.sizeUpdated = 0;
  }

  ngOnInit() {
    this.formUpdate = this.formBuilder.group({
      dateFrom: new FormControl(new Date(), { validators: [Validators.required, DateTimeValidator] }),
      dateTo: new FormControl(new Date(), { validators: [Validators.required, DateTimeValidator] }),
    }, { updateOn: 'change' });
  }

  updateParticipants() {
    this.processing = true;
    const fromDate = new Date(this.formUpdate.value.dateFrom);
    const toDate = new Date(this.formUpdate.value.dateTo);
    if (toDate < fromDate) {
      alert("Time from must be less than time to.");
      return;
    }
    this.apiService.updateParticipants(fromDate.toLocaleString(), toDate.toLocaleString()).subscribe(
      sizeUpdated => {
        this.processing = false;
        this.sizeUpdated = sizeUpdated
      },
      error => this.errorMessage = <any>error
    );
  }
}

export const DateTimeValidator = (fc: FormControl) => {
  const date = new Date(fc.value);
  const isValid = !isNaN(date.valueOf());
  return isValid ? null : {
    isValid: {
      valid: false
    }
  };
};
