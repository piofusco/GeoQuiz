import android.os.Build;
import android.widget.Button;
import android.widget.ImageButton;

import com.bignerdranch.android.geoquiz.BuildConfig;
import com.bignerdranch.android.geoquiz.QuizActivity;
import com.bignerdranch.android.geoquiz.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.N_MR1)
@RunWith(RobolectricTestRunner.class)
public class QuizActivityTest {
    private QuizActivity subject;

    @Before
    public void setup() {
        subject = Robolectric.setupActivity(QuizActivity.class);
    }

    @Test
    public void whenTrueButtonIsPressed_shouldShowToastWithCorrectAndPercentageScore() {
        Button trueButton = (Button) subject.findViewById(R.id.true_button);
        Button falseButton = (Button) subject.findViewById(R.id.false_button);
        ImageButton nextButton = (ImageButton) subject.findViewById(R.id.next_button);

        trueButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast(), equalTo("Correct! Your score: 100%"));

        nextButton.performClick();
        falseButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast(), equalTo("Incorrect! Your score: 50%"));
    }
}