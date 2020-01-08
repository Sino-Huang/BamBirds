// THIS FILE WAS AUTOMATICALLY GENERATED BY 'src/Python/decision_tree/decision_tree.py'

package level_selection.model_representation;

public class DecisionTreeRegressor {
public static int[] predict(int max_score, int num_birds, int num_destroyable_objects, int num_generated_shots, int num_line_segments_hills, int num_pigs, int num_strategies, int num_times_played, int numerical_strategies) {
  if (num_destroyable_objects <= 33.5){
    if (num_destroyable_objects <= 14.5){
      if (num_pigs <= 3.5){
        if (num_generated_shots <= 18.0){
          if (num_destroyable_objects <= 2.5){
            return new int[] {5040};
          }else  /* if num_destroyable_objects > 2.5*/{
            return new int[] {7938};
          }
        }else  /* if num_generated_shots > 18.0*/{
          if (num_generated_shots <= 60.0){
            return new int[] {13244};
          }else  /* if num_generated_shots > 60.0*/{
            return new int[] {19272};
          }
        }
      }else  /* if num_pigs > 3.5*/{
        if (num_strategies <= 7.0){
          return new int[] {34250};
        }else  /* if num_strategies > 7.0*/{
          if (num_times_played <= 0.5){
            return new int[] {27355};
          }else  /* if num_times_played > 0.5*/{
            return new int[] {23086};
          }
        }
      }
    }else  /* if num_destroyable_objects > 14.5*/{
      if (num_pigs <= 6.5){
        if (max_score <= 38410.0){
          if (num_generated_shots <= 204.0){
            return new int[] {31110};
          }else  /* if num_generated_shots > 204.0*/{
            return new int[] {6640};
          }
        }else  /* if max_score > 38410.0*/{
          if (max_score <= 55765.0){
            return new int[] {41503};
          }else  /* if max_score > 55765.0*/{
            return new int[] {57715};
          }
        }
      }else  /* if num_pigs > 6.5*/{
        if (num_strategies <= 40.5){
          if (num_generated_shots <= 143.0){
            return new int[] {65652};
          }else  /* if num_generated_shots > 143.0*/{
            return new int[] {78427};
          }
        }else  /* if num_strategies > 40.5*/{
          if (max_score <= 29385.0){
            return new int[] {59140};
          }else  /* if max_score > 29385.0*/{
            return new int[] {48936};
          }
        }
      }
    }
  }else  /* if num_destroyable_objects > 33.5*/{
    if (max_score <= 75785.0){
      if (num_destroyable_objects <= 46.5){
        if (max_score <= 52390.0){
          if (num_pigs <= 1.5){
            return new int[] {25548};
          }else  /* if num_pigs > 1.5*/{
            return new int[] {45999};
          }
        }else  /* if max_score > 52390.0*/{
          if (max_score <= 65680.0){
            return new int[] {55228};
          }else  /* if max_score > 65680.0*/{
            return new int[] {66904};
          }
        }
      }else  /* if num_destroyable_objects > 46.5*/{
        if (num_destroyable_objects <= 77.5){
          if (numerical_strategies <= 37.5){
            return new int[] {67998};
          }else  /* if numerical_strategies > 37.5*/{
            return new int[] {57230};
          }
        }else  /* if num_destroyable_objects > 77.5*/{
          if (num_strategies <= 21.0){
            return new int[] {81165};
          }else  /* if num_strategies > 21.0*/{
            return new int[] {98559};
          }
        }
      }
    }else  /* if max_score > 75785.0*/{
      if (max_score <= 101800.0){
        if (num_pigs <= 7.5){
          if (num_generated_shots <= 297.0){
            return new int[] {71675};
          }else  /* if num_generated_shots > 297.0*/{
            return new int[] {83475};
          }
        }else  /* if num_pigs > 7.5*/{
          if (max_score <= 92345.0){
            return new int[] {93236};
          }else  /* if max_score > 92345.0*/{
            return new int[] {89135};
          }
        }
      }else  /* if max_score > 101800.0*/{
        if (max_score <= 104690.0){
          if (num_times_played <= 3.5){
            return new int[] {96922};
          }else  /* if num_times_played > 3.5*/{
            return new int[] {110535};
          }
        }else  /* if max_score > 104690.0*/{
          if (num_birds <= 3.5){
            return new int[] {109228};
          }else  /* if num_birds > 3.5*/{
            return new int[] {118820};
          }
        }
      }
    }
  }
}
}