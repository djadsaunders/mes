import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ResourceComponent } from './resource/resource.component';
import { DashboardComponent } from './dashboard/dashboard.component';

const routes: Routes = [
	{path: '', redirectTo: 'dashboard', pathMatch: 'full'},
	{path: 'dashboard', component: DashboardComponent},
    {path: 'resource/:tag', component: ResourceComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
