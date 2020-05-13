import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DashboardRowComponent } from './dashboard-row/dashboard-row.component';
import { ResourceComponent } from './resource/resource.component';
import { AppRoutingModule } from './app-routing.module';
import { CrewListComponent } from './crew-list/crew-list.component';
import { ShiftListComponent } from './shift-list/shift-list.component';
import { Globals } from './globals';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    DashboardRowComponent,
    ResourceComponent,
    CrewListComponent,
    ShiftListComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  entryComponents: [
    ShiftListComponent,
    CrewListComponent
  ],
  providers: [Globals],
  bootstrap: [AppComponent]
})
export class AppModule { }
