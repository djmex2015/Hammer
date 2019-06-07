import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
// import { DlDateTimeDateModule, DlDateTimePickerModule } from 'angular-bootstrap-datetimepicker';
// import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { library as fontLibrary } from '@fortawesome/fontawesome-svg-core';
import { faCalendar,  faClock } from '@fortawesome/free-regular-svg-icons';


import { AppComponent } from './app.component';
import { DateTimePickerComponent } from './date-time-picker/date-time-picker.component';
// import { NgbTimepicker} from '@ng-bootstrap/ng-bootstrap';
// import { FilterRaioComponent } from './filter-raio/filter-raio.component';


fontLibrary.add(
  faCalendar,
  faClock
);

@NgModule({
  declarations: [
    AppComponent,
    DateTimePickerComponent,
    // FilterRaioComponent,
  ],
  imports: [
    BrowserModule,
    // RouterModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule.forRoot(),
    FontAwesomeModule,
    // OwlDateTimeModule,
    // OwlNativeDateTimeModule,

    // DlDateTimeDateModule,  // <--- Determines the data type of the model
    // DlDateTimePickerModule,
    // RouterModule.forRoot([
    //   { path: 'filterRaio', component: FilterRaioComponent },
    //   { path: '', redirectTo: 'lines', pathMatch: 'full' },
    //   { path: '**', redirectTo: 'lines', pathMatch: 'full' }
    // ], { useHash: true })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
