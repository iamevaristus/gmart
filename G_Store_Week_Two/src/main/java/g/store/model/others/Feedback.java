//package g.store.model.others;
//
//import g.store.enums.auth.ActionStatus;
//import g.store.enums.others.FeedbackMode;
//import g.store.enums.others.FeedbackStatus;
//import g.store.enums.others.ComplaintStatus;
//import g.store.enums.others.RatingStar;
//import g.store.exception.AuthorizationException;
//import g.store.services.FeedbackService;
//
//import java.util.Date;
//
//public class Feedback implements FeedbackService {
//    /*
//    This is the detail of the feedback.
//     */
//    private String feedback;
//
//    /*
//    THis is the type of the feedback.
//     */
//    private FeedbackStatus type;
//
//    /*
//    Date feedback was given.
//     */
//    private Date date;
//
//    /*
//    Status of the feedback
//     */
//    private ComplaintStatus status;
//
//    /*
//    Feedback Mode
//     */
//    private FeedbackMode feedbackMode;
//
//    /*
//    Rating Stars
//     */
//    private RatingStar ratingStar;
//
//    public Feedback(
//            String feedback,
//            FeedbackStatus type,
//            Date date,
//            ComplaintStatus status
//    ) {
//        this.feedback = feedback;
//        this.type = type;
//        this.date = date;
//        this.status = status;
//    }
//
//    public Feedback(
//            String feedback,
//            FeedbackStatus type,
//            Date date,
//            ComplaintStatus status,
//            FeedbackMode feedbackMode,
//            RatingStar ratingStar
//    ) {
//        this.feedback = feedback;
//        this.type = type;
//        this.date = date;
//        this.status = status;
//        this.feedbackMode = feedbackMode;
//        this.ratingStar = ratingStar;
//    }
//
//    public FeedbackMode getFeedbackMode() {
//        return feedbackMode;
//    }
//
//    public void setFeedbackMode(FeedbackMode feedbackMode) {
//        this.feedbackMode = feedbackMode;
//    }
//
//    public RatingStar getRatingStar() {
//        return ratingStar;
//    }
//
//    public void setRatingStar(RatingStar ratingStar) {
//        this.ratingStar = ratingStar;
//    }
//
//    public Feedback() {}
//
//    public String getFeedback() {
//        return feedback;
//    }
//
//    public void setFeedback(String feedback) {
//        this.feedback = feedback;
//    }
//
//    public FeedbackStatus getType() {
//        return type;
//    }
//
//    public void setType(FeedbackStatus type) {
//        this.type = type;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public ComplaintStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(ComplaintStatus status) {
//        this.status = status;
//    }
//
//    public boolean isGoodReviewFeedback() {
//        return this.type == FeedbackStatus.GOOD_REVIEW;
//    }
//
//    public boolean isBadReviewFeedback() {
//        return this.type == FeedbackStatus.BAD_REVIEW;
//    }
//
//    public boolean isReviewFeedback() {
//        return this.type == FeedbackStatus.REVIEW;
//    }
//
//    public boolean isComplaintFeedback() {
//        return this.type == FeedbackStatus.COMPLAINT;
//    }
//
//    public boolean isResolved() {
//        return this.status == ComplaintStatus.RESOLVED;
//    }
//
//    public boolean isPending() {
//        return this.status == ComplaintStatus.PENDING;
//    }
//
//    public boolean isEscalating() {
//        return this.status == ComplaintStatus.ESCALATING;
//    }
//
//    public boolean isNotResolved() {
//        return this.status == ComplaintStatus.NOT_RESOLVED;
//    }
//
//    public boolean isNotComplaint() {
//        return this.status == ComplaintStatus.NOT_COMPLAINT;
//    }
//
//    @Override
//    public ActionStatus giveFeedback(Feedback complaint) {
//        return null;
//    }
//
//    @Override
//    public ComplaintStatus resolveComplaints(Feedback complaint) throws AuthorizationException {
//        return null;
//    }
//
//    @Override
//    public ComplaintStatus escalateComplaints(Feedback complaint) throws AuthorizationException {
//        return null;
//    }
//
//    @Override
//    public ComplaintStatus pendComplaints(Feedback complaint) throws AuthorizationException {
//        return null;
//    }
//}
