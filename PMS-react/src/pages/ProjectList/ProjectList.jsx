import { Card } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import {
  MagnifyingGlassIcon,
  MixerHorizontalIcon,
} from "@radix-ui/react-icons";
import { CardContent } from "@/components/ui/card";
import { ScrollArea } from "@/components/ui/scroll-area";
import { RadioGroup } from "@/components/ui/radio-group";
import { RadioGroupItem } from "@/components/ui/radio-group";
import { Label } from "@/components/ui/label";
import { Input } from "@/components/ui/input";
import { useEffect, useState } from "react";
import ProjectCard from "../Project/ProjectCard";
import { useDispatch, useSelector } from "react-redux";
import { fetchProjects, searchProjects } from "@/Redux/Project/Action";

export const tags = [
  "all",
  "react",
  "spring boot",
  "angular",
  "mysql",
  "nextjs",
  "mongodb",
  "python",
  "express",
  "django",
  "flask",
];

const ProjectList = () => {
  const [keyword, setKeyword] = useState("");
  const { project } = useSelector((store) => store);
  const dispatch = useDispatch();
  const handleFilterCategory = (value) => {
    if(value === "all") return dispatch(fetchProjects({}));
    dispatch(fetchProjects({ category: value }));
    // console.log("value", value, section);
  };
  const handleFilterTags = (value) => {
    if(value === "all") return dispatch(fetchProjects({}));
    dispatch(fetchProjects({ tag: value }));
    // console.log("value", value, section);
  };
  const handleSearchChange = (e) => {
    setKeyword(e.target.value);
    dispatch(searchProjects({ keyword: e.target.value }));
  };

  console.log("project store", project)
  return (
    <>
      <div className="relative px-5 lg:px-0 lg:flex gap-20 justify-center py-10">
        <section className="filterSection ">
          <Card className="p-5 sticky top-10">
            <div className="flex justify-between lg:w-[20rem]">
              <p className="text-x1 -tracking-wider">filters</p>
              <Button variant="ghost" size="icon">
                <MixerHorizontalIcon></MixerHorizontalIcon>
              </Button>
            </div>
            <CardContent className="mt-5">
              <ScrollArea className="space-y-7 h-[70vh]">
                <div>
                  <h1 className="pb-3 text-gray-400 border-b">Category</h1>
                  <div className="pt-5">
                    <RadioGroup
                      className="space-y-3 pt-5"
                      defaultValue="all"
                      onValueChange={(value) =>
                        handleFilterCategory(value)
                      }
                    >
                      <div className="flex items-center gap-2">
                        <RadioGroupItem value="all" id="r1" />
                        <Label htmlFor="r1">all</Label>
                      </div>
                      <div className="flex items-center gap-2">
                        <RadioGroupItem value="fullstack" id="r2" />
                        <Label htmlFor="r2">fullstack</Label>
                      </div>
                      <div className="flex items-center gap-2">
                        <RadioGroupItem value="frontend" id="r3" />
                        <Label htmlFor="r3">frontend</Label>
                      </div>
                      <div className="flex items-center gap-2">
                        <RadioGroupItem value="backend" id="r4" />
                        <Label htmlFor="r4">backend</Label>
                      </div>
                    </RadioGroup>
                  </div>
                </div>

                <div className="pt-9">
                  <h1 className="pb-3 text-gray-400 border-b">Tag</h1>
                  <div className="pt-5">
                    <RadioGroup
                      className="space-y-3 pt-5"
                      defaultValue="all"
                      onValueChange={(value) =>
                        handleFilterTags(value)
                      }
                    >
                      {tags.map((tag) => (
                        <div key={tag} className="flex items-center gap-2 ">
                          <RadioGroupItem value={tag} id={`r1-${tag}`} />
                          <Label htmlFor={`r1-${tag}`}>{tag}</Label>
                        </div>
                      ))}
                    </RadioGroup>
                  </div>
                </div>
              </ScrollArea>
            </CardContent>
          </Card>
        </section>
        <section className="projectListSection w-full lg:w-[48rem]">
          <div className="flex gap-2 items-center pb-5 justify-between">
            <div className="relative p-0 w-full">
              <Input
                onChange={handleSearchChange}
                placeholder="Search projects"
                className="40% px-9"
              />
              <MagnifyingGlassIcon className="absolute top-3 left-4" />
            </div>
          </div>
          <div className="space-y-5 min-h-[74vh]">
            {keyword && project.searchProjects
              ? project.searchProjects.map((item, index) => (
                <ProjectCard item={item} key={`${item.id}-${index}`} />
                ))
              : project.projects.map((item) => (
                  <ProjectCard key={item.id} item={item} />
                ))}
          </div>
        </section>
      </div>
    </>
  );
};

export default ProjectList;
