import * as actionTypes from "./ActionTypes";
const initialState = {
  issues: [],
  loading: false,
  error: null,
  issueDetails: null,
};
const issueReducer = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.FETCH_ISSUE_REQUEST:
    case actionTypes.FETCH_ISSUES_BY_ID_REQUEST:
    case actionTypes.CREATE_ISSUE_REQUEST:
    case actionTypes.DELETE_ISSUE_REQUEST:
    case actionTypes.ASSIGNED_ISSUE_TO_USER_REQUEST:
      return {
        ...state,
        loading: true,
        error: null,
      };
    case actionTypes.FETCH_ISSUE_SUCCESS:
      return {
        ...state,
        issues: action.issues,
        loading: false,
      };
    case actionTypes.FETCH_ISSUES_BY_ID_SUCCESS:
    case actionTypes.UPDATE_ISSUE_STATUS_SUCCESS:
      return {
        ...state,
        issueDetails: action.issues,
        loading: false,
      };
    case actionTypes.CREATE_ISSUE_SUCCESS:
      return {
        ...state,
        issues: [...state.issues, action.issue],
        loading: false,
      };
    case actionTypes.ASSIGNED_ISSUE_TO_USER_SUCCESS:
      return {
        ...state,
        loading: false,
        issues: state.issues.map((issue) =>
          issue.id === action.issue.id ? action.issue : issue
        ),
      };
    case actionTypes.DELETE_ISSUE_SUCCESS:
      const updatedIssues = state.issues.filter(
        (issue) => issue.id !== action.issue.id
      );
      return {
        ...state,
        issues: updatedIssues,
        loading: false, // Add this to stop loading indicator
      };

    case actionTypes.FETCH_ISSUE_FAILURE:
    case actionTypes.CREATE_ISSUE_FAILURE:
    case actionTypes.DELETE_ISSUE_FAILURE:
    case actionTypes.ASSIGNED_ISSUE_TO_USER_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.error,
      };

    default:
      return state;
  }
};

export default issueReducer;
