import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ResultsViewComponent } from './results/results-view/results-view.component';
import { TeamsOverviewComponent } from './teams/teams-overview/teams-overview.component';
import { TeamInfoComponent } from './teams/team-info/team-info.component';


const routes: Routes = [
  {path: 'results', component: ResultsViewComponent},
  {path: 'teams', component: TeamsOverviewComponent},
  {path: 'teams/:teamId', component: TeamInfoComponent}
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})

export class AppRoutingModule { }
