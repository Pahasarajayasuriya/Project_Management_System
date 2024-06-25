import api, { API_BASE_URL } from "@/config/api";
import {
  ACCEPT_INVITE_REQUEST,
  ACCEPT_INVITE_SUCCESS,
  CREATE_PROJECT_REQUEST,
  CREATE_PROJECT_SUCCESS,
  DELETE_PROJECT_REQUEST,
  DELETE_PROJECT_SUCCESS,
  FETCH_PROJECTS_REQUEST,
  FETCH_PROJECTS_SUCCESS,
  FETCH_PROJECT_BY_ID_REQUEST,
  FETCH_PROJECT_BY_ID_SUCCESS,
  INVITE_PROJECT_REQUEST,
  INVITE_PROJECT_SUCCESS,
  SEARCH_PROJECTS_REQUEST,
  SEARCH_PROJECTS_SUCCESS,
} from "./ActionTypes";

export const fetchProjects =
  ({ category, tag }) =>
  async (dispatch) => {
    dispatch({ type: FETCH_PROJECTS_REQUEST });
    try {
      const { data } = await api.get("/api/projects", {
        params: { category, tag },
      });
      console.log("all projects", data);
      dispatch({ type: FETCH_PROJECTS_SUCCESS, projects: data });
    } catch (error) {
      console.log("error:", error);
    }
  };

export const searchProjects =
  ({ keyword }) =>
  async (dispatch) => {
    dispatch({ type: SEARCH_PROJECTS_REQUEST });
    try {
      const { data } = await api.get("/api/projects/search?keyword=" + keyword);
      console.log("search projects", data);
      dispatch({ type: SEARCH_PROJECTS_SUCCESS, projects: data });
    } catch (error) {
      console.log("error:", error);
    }
  };

export const createProjects = (projectData) => async (dispatch) => {
  dispatch({ type: CREATE_PROJECT_REQUEST });
  try {
    const { data } = await api.post("/api/projects", projectData);
    console.log("create projects", data);
    dispatch({ type: CREATE_PROJECT_SUCCESS, project: data });
  } catch (error) {
    console.log("error:", error);
  }
};

export const fetchProjectById = (id) => async (dispatch) => {
  dispatch({ type: FETCH_PROJECT_BY_ID_REQUEST });
  try {
    const { data } = await api.get("/api/projects/" + id);
    console.log("project", data);
    dispatch({ type: FETCH_PROJECT_BY_ID_SUCCESS, project: data });
  } catch (error) {
    console.log("error:", error);
  }
};

export const deleteProject =
  ({ projectId }) =>
  async (dispatch) => {
    dispatch({ type: DELETE_PROJECT_REQUEST });
    try {
      const { data } = await api.delete("/api/projects/" + projectId);
      console.log("delete project", data);
      dispatch({ type: DELETE_PROJECT_SUCCESS, projectId });
    } catch (error) {
      console.log("error:", error);
    }
  };

export const InviteToProject =
  ({ email, projectId }) =>
  async (dispatch) => {
    dispatch({ type: INVITE_PROJECT_REQUEST });
    try {
      const { data } = await api.post("/api/projects/invite", {
        email,
        projectId,
      });
      console.log("Invite response:", data); // Add logging
      dispatch({ type: INVITE_PROJECT_SUCCESS, payload: data });
    } catch (error) {
      console.error(
        "Invite error:",
        error.response ? error.response.data : error.message
      ); // Enhanced error logging
      // Optionally, dispatch an error action to handle it in the UI
    }
  };

export const acceptInvite =
  ({ token, navigate }) =>
  async (dispatch) => {
    dispatch({ type: ACCEPT_INVITE_REQUEST });
    try {
      const { data } = await api.get("/api/projects/accept_invitation", {
        params: { token },
      });
      navigate("/project/" + data.projectId);
      console.log("accept invitation", data);
      dispatch({ type: ACCEPT_INVITE_SUCCESS, payload: data });
    } catch (error) {
      console.log("error:", error);
    }
  };
