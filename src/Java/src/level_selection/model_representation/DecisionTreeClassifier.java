// THIS FILE WAS AUTOMATICALLY GENERATED BY 'src/Python/decision_tree/decision_tree.py'

package level_selection.model_representation;

public class DecisionTreeClassifier {
public static int[] predict(int max_score, int num_birds, int num_destroyable_objects, int num_generated_shots, int num_line_segments_hills, int num_pigs, int num_strategies, int num_times_played, int numerical_strategies) {
  if (num_pigs <= 5.945467476241974){
    if (num_destroyable_objects <= 46.173317146568245){
      if (max_score <= 40110.81148122941){
        if (num_destroyable_objects <= 33.80704806177274){
          if (num_strategies <= 0.5708683368614261){
            return new int[] {19,6};
          }else  /* if num_strategies > 0.5708683368614261*/{
            return new int[] {67,200};
          }
        }else  /* if num_destroyable_objects > 33.80704806177274*/{
          if (max_score <= 21674.523400251997){
            return new int[] {61,26};
          }else  /* if max_score > 21674.523400251997*/{
            return new int[] {10,12};
          }
        }
      }else  /* if max_score > 40110.81148122941*/{
        if (numerical_strategies <= 43.8856179825429){
          if (num_strategies <= 22.881705104783972){
            return new int[] {5,45};
          }else  /* if num_strategies > 22.881705104783972*/{
            return new int[] {8,11};
          }
        }else  /* if numerical_strategies > 43.8856179825429*/{
          return new int[] {0,25};
        }
      }
    }else  /* if num_destroyable_objects > 46.173317146568245*/{
      if (num_line_segments_hills <= 1.0072953566011484){
        if (num_pigs <= 4.717701395376446){
          if (num_strategies <= 53.61888345126942){
            return new int[] {2,13};
          }else  /* if num_strategies > 53.61888345126942*/{
            return new int[] {0,15};
          }
        }else  /* if num_pigs > 4.717701395376446*/{
          return new int[] {5,0};
        }
      }else  /* if num_line_segments_hills > 1.0072953566011484*/{
        if (max_score <= 23888.79132659118){
          if (numerical_strategies <= 60.17599628128856){
            return new int[] {154,20};
          }else  /* if numerical_strategies > 60.17599628128856*/{
            return new int[] {5,4};
          }
        }else  /* if max_score > 23888.79132659118*/{
          if (num_line_segments_hills <= 9.72095177728727){
            return new int[] {13,27};
          }else  /* if num_line_segments_hills > 9.72095177728727*/{
            return new int[] {23,14};
          }
        }
      }
    }
  }else  /* if num_pigs > 5.945467476241974*/{
    if (max_score <= 57237.041145487245){
      if (max_score <= 33145.087572645905){
        if (num_line_segments_hills <= 8.201526637748595){
          if (num_times_played <= 1.9997406089677199){
            return new int[] {44,12};
          }else  /* if num_times_played > 1.9997406089677199*/{
            return new int[] {44,5};
          }
        }else  /* if num_line_segments_hills > 8.201526637748595*/{
          if (numerical_strategies <= 62.700323617411925){
            return new int[] {258,22};
          }else  /* if numerical_strategies > 62.700323617411925*/{
            return new int[] {51,11};
          }
        }
      }else  /* if max_score > 33145.087572645905*/{
        if (num_generated_shots <= 357.8123631830385){
          if (num_strategies <= 22.616240029975884){
            return new int[] {1,17};
          }else  /* if num_strategies > 22.616240029975884*/{
            return new int[] {3,1};
          }
        }else  /* if num_generated_shots > 357.8123631830385*/{
          return new int[] {2,0};
        }
      }
    }else  /* if max_score > 57237.041145487245*/{
      if (num_strategies <= 184.48328242520023){
        if (num_line_segments_hills <= 63.98541095153681){
          if (num_generated_shots <= 142.00505332835255){
            return new int[] {3,15};
          }else  /* if num_generated_shots > 142.00505332835255*/{
            return new int[] {43,48};
          }
        }else  /* if num_line_segments_hills > 63.98541095153681*/{
          return new int[] {0,3};
        }
      }else  /* if num_strategies > 184.48328242520023*/{
        return new int[] {2,0};
      }
    }
  }
}
}