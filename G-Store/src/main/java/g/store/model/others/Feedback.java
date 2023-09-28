package g.store.model.others;

import g.store.types.FeedbackTypes;
import g.store.types.ComplaintStatus;

import java.util.Date;

public class Feedback {
    /*
    This is the detail of the feedback.
     */
    private String feedback;

    /*
    THis is the type of the feedback.
     */
    private FeedbackTypes type;

    /*
    Date feedback was given.
     */
    private Date date;

    /*
    Status of the feedback
     */
    private ComplaintStatus status;

    public Feedback(
            String feedback,
            FeedbackTypes type,
            Date date,
            ComplaintStatus status
    ) {
        this.feedback = feedback;
        this.type = type;
        this.date = date;
        this.status = status;
    }

    public Feedback() {}

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public FeedbackTypes getType() {
        return type;
    }

    public void setType(FeedbackTypes type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }

    public boolean isGoodReviewFeedback() {
        return this.type == FeedbackTypes.GOOD_REVIEW;
    }

    public boolean isBadReviewFeedback() {
        return this.type == FeedbackTypes.BAD_REVIEW;
    }

    public boolean isReviewFeedback() {
        return this.type == FeedbackTypes.REVIEW;
    }

    public boolean isComplaintFeedback() {
        return this.type == FeedbackTypes.COMPLAINT;
    }

    public boolean isResolved() {
        return this.status == ComplaintStatus.RESOLVED;
    }

    public boolean isPending() {
        return this.status == ComplaintStatus.PENDING;
    }

    public boolean isEscalating() {
        return this.status == ComplaintStatus.ESCALATING;
    }

    public boolean isNotResolved() {
        return this.status == ComplaintStatus.NOT_RESOLVED;
    }

    public boolean isNotComplaint() {
        return this.status == ComplaintStatus.NOT_COMPLAINT;
    }
}
