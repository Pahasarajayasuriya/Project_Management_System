import * as actionTypes from "./ActionTypes";

const initialState = {
  messages: [],
  loading: false,
  error: null,
  chat: null,
};

export const chatReducer = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.SEND_MESSAGE_REQUEST:
    case actionTypes.FETCH_CHAT_MESSAGES_REQUEST:
      return {
        ...state,
        loading: true,
        error: null,
      };
    case actionTypes.SEND_MESSAGE_SUCCESS:
      return {
        ...state,
        messages: [...state.messages, action.message], // Append new message
        loading: false,
      };
    case actionTypes.FETCH_CHAT_BY_PROJECT_SUCCESS:
      return {
        ...state,
        chat: action.message,
        loading: false,
      };
    case actionTypes.FETCH_CHAT_MESSAGES_SUCCESS:
      return {
        ...state,
        messages: action.message, // Replace messages with fetched messages
        loading: false,
      };
    case actionTypes.SEND_MESSAGE_FAILURE:
    case actionTypes.FETCH_CHAT_MESSAGES_FAILURE:
    case actionTypes.FETCH_CHAT_BY_PROJECT_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.error,
      };
    default:
      return state;
  }
};

export default chatReducer;
