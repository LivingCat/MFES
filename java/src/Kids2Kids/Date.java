package Kids2Kids;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Date {
  private Number day;
  private Number month;
  private Number year;

  public void cg_init_Date_1(final Number d, final Number m, final Number y) {

    Number atomicTmp_1 = d;
    Number atomicTmp_2 = m;
    Number atomicTmp_3 = y;
    {
        /* Start of atomic statement */
      day = atomicTmp_1;
      month = atomicTmp_2;
      year = atomicTmp_3;
    } /* End of atomic statement */

    return;
  }

  private Number daysOfMonth(final Number m, final Number y) {

    Boolean orResult_1 = false;

    if (Utils.equals(m, 4L)) {
      orResult_1 = true;
    } else {
      Boolean orResult_2 = false;

      if (Utils.equals(m, 6L)) {
        orResult_2 = true;
      } else {
        Boolean orResult_3 = false;

        if (Utils.equals(m, 9L)) {
          orResult_3 = true;
        } else {
          orResult_3 = Utils.equals(m, 11L);
        }

        orResult_2 = orResult_3;
      }

      orResult_1 = orResult_2;
    }

    if (orResult_1) {
      return 30L;

    } else if (Utils.equals(m, 2L)) {
      Boolean orResult_4 = false;

      Boolean andResult_1 = false;

      if (Utils.equals(Utils.mod(y.longValue(), 4L), 0L)) {
        if (!(Utils.equals(Utils.mod(y.longValue(), 100L), 0L))) {
          andResult_1 = true;
        }
      }

      if (andResult_1) {
        orResult_4 = true;
      } else {
        orResult_4 = Utils.equals(Utils.mod(y.longValue(), 400L), 0L);
      }

      Boolean leapyear = orResult_4;
      if (leapyear) {
        return 29L;

      } else {
        return 28L;
      }

    } else {
      return 31L;
    }
  }

  private Character digitToChar(final Number n) {

    Number intPattern_1 = n;
    Boolean success_1 = Utils.equals(intPattern_1, 0L);

    if (!(success_1)) {
      Number intPattern_2 = n;
      success_1 = Utils.equals(intPattern_2, 1L);

      if (!(success_1)) {
        Number intPattern_3 = n;
        success_1 = Utils.equals(intPattern_3, 2L);

        if (!(success_1)) {
          Number intPattern_4 = n;
          success_1 = Utils.equals(intPattern_4, 3L);

          if (!(success_1)) {
            Number intPattern_5 = n;
            success_1 = Utils.equals(intPattern_5, 4L);

            if (!(success_1)) {
              Number intPattern_6 = n;
              success_1 = Utils.equals(intPattern_6, 5L);

              if (!(success_1)) {
                Number intPattern_7 = n;
                success_1 = Utils.equals(intPattern_7, 6L);

                if (!(success_1)) {
                  Number intPattern_8 = n;
                  success_1 = Utils.equals(intPattern_8, 7L);

                  if (!(success_1)) {
                    Number intPattern_9 = n;
                    success_1 = Utils.equals(intPattern_9, 8L);

                    if (success_1) {
                      return '8';

                    } else {
                      return '9';
                    }

                  } else {
                    return '7';
                  }

                } else {
                  return '6';
                }

              } else {
                return '5';
              }

            } else {
              return '4';
            }

          } else {
            return '3';
          }

        } else {
          return '2';
        }

      } else {
        return '1';
      }

    } else {
      return '0';
    }
  }

  private String natToString(final Number n) {

    String string = SeqUtil.toStr(SeqUtil.seq());
    Number cpy = n;
    Boolean whileCond_1 = true;
    while (whileCond_1) {
      whileCond_1 = cpy.longValue() > 0L;
      if (!(whileCond_1)) {
        break;
      }

      {
        Number digit = Utils.mod(cpy.longValue(), 10L);
        cpy = Utils.div(cpy.longValue(), 10L);
        string = new String(new char[] {digitToChar(digit)}) + string;
      }
    }

    return string;
  }

  public String getDate() {

    String d = SeqUtil.toStr(SeqUtil.seq());
    String m = SeqUtil.toStr(SeqUtil.seq());
    if (day.longValue() < 10L) {
      d = d + new String(new char[] {'0'});
    }

    if (month.longValue() < 10L) {
      m = m + new String(new char[] {'0'});
    }

    return d
        + natToString(day)
        + new String(new char[] {'/'})
        + m
        + natToString(month)
        + new String(new char[] {'/'})
        + natToString(year);
  }

  public Date(final Number d, final Number m, final Number y) {

    cg_init_Date_1(d, m, y);
  }

  public Date() {}

  public String toString() {

    return "Date{"
        + "day := "
        + Utils.toString(day)
        + ", month := "
        + Utils.toString(month)
        + ", year := "
        + Utils.toString(year)
        + "}";
  }
}
