import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ResultsViewComponent } from './results/results-view/results-view.component';
import { TeamsOverviewComponent } from './teams/teams-overview/teams-overview.component';


const routes: Routes = [
  {path: 'results', component: ResultsViewComponent},
  {path: 'teams', component: TeamsOverviewComponent},
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})

export class AppRoutingModule { }
